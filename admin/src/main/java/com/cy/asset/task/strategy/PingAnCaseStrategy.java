package com.cy.asset.task.strategy;

import com.cy.asset.common.util.BeanContext;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.customer.bean.AddressBean;
import com.cy.asset.customer.bean.AvailableEnum;
import com.cy.asset.customer.bean.PhoneBean;
import com.cy.asset.customer.bean.PhoneTypeEnum;
import com.cy.asset.customer.bean.RelationEnum;
import com.cy.asset.customer.bean.SensitiveEnum;
import com.cy.asset.customer.dao.CustomerDao;
import com.cy.asset.customer.service.Impl.CustomerServiceImpl;
import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.CaseEnum;
import com.cy.asset.task.bean.CaseImportBean;
import com.cy.asset.customer.bean.CustomerBean;
import com.cy.asset.task.bean.CollectStatusEnum;
import com.cy.asset.task.bean.PingAnCase;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.dao.CaseDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wc
 * @date 2020/12/4
 */
public class PingAnCaseStrategy implements CaseStrategy {

    private CaseDao caseDao = BeanContext.getApplicationContext().getBean(CaseDao.class);
    private CustomerDao customerDao = BeanContext.getApplicationContext().getBean(CustomerDao.class);

    private CustomerServiceImpl customerService = BeanContext.getApplicationContext().getBean(CustomerServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean generateCase(List<Map<String,Object>> caseMap, CaseImportBean caseImport) {
        ResultBean result = new ResultBean();
        // 成功案件数统计
        AtomicReference<Integer> succeedCount = new AtomicReference<>(0);
        // 案件总金额统计
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(new BigDecimal(0.00));
        List<PingAnCase> pingAnCaseList = BeanToMapUtil.convertListMap2ListBean(caseMap, PingAnCase.class);
        List<CustomerBean> customerList = new ArrayList<>();
        List<CaseBean> caseList = new ArrayList<>();
        List<PingAnCase> pingAnList = new ArrayList<>();
        // 客户手机信息
        List<PhoneBean> phoneList = new ArrayList<>();
        // 客户地址信息
        List<AddressBean> addressList = new ArrayList<>();
        pingAnCaseList.forEach((pingAnCase)->{
            CustomerBean customer = new CustomerBean();
            CaseBean caseBean = new CaseBean();
            caseBean.setBatchCode(caseImport.getBatchCode());
            caseBean.setCollectStatus(CollectStatusEnum.NEW_CASE.collectType());
            caseBean.setCaseSource(CaseEnum.PING_AN.caseType());
            caseBean.setCurrent30Day(pingAnCase.getIntegration30Day());
            // 将平安案件信息拷贝到客户和个案的bean属性中
            BeanUtils.copyProperties(pingAnCase,customer);
            BeanUtils.copyProperties(pingAnCase,caseBean);
            // 客户手机和地址信息
            initialCustomerInfo(pingAnCase,phoneList,addressList);
            // 获取客户基本信息
            customer = customerService.generateCustomerInfo(customer);

            customerList.add(customer);
            caseList.add(caseBean);
            pingAnList.add(pingAnCase);
            // 生成客户手机信息
            if( phoneList.size() >= 500 ){
                customerDao.saveCustomerPhone(phoneList);
                phoneList.clear();
            }
            // 生成客户地址信息
            if( addressList.size() >= 500 ){
                customerDao.saveCustomerAddress(addressList);
                addressList.clear();
            }
            // 生成客户信息
            if( customerList.size() >= 500 ){
                customerDao.saveCustomer(customerList);
                customerList.clear();
            }
            // 生成个案信息
            if( caseList.size() >= 500 ){
                caseDao.saveCase(caseList);
                caseList.clear();
            }
            // 平安案件信息
            if( caseList.size() >= 200 ){
                caseDao.savePingAnCase(pingAnList);
                pingAnList.clear();
            }
            totalAmount.set(totalAmount.get().add((pingAnCase.getAppointCaseAmount())));
            succeedCount.getAndSet(succeedCount.get() + 1);
        });
        if(CollectionUtils.isNotEmpty(customerList)){
            customerDao.saveCustomer(customerList);
        }
        if(CollectionUtils.isNotEmpty(addressList)){
            customerDao.saveCustomerAddress(addressList);
        }
        if(CollectionUtils.isNotEmpty(phoneList)){
            customerDao.saveCustomerPhone(phoneList);
        }
        if(CollectionUtils.isNotEmpty(caseList)){
            caseDao.saveCase(caseList);
        }
        if(CollectionUtils.isNotEmpty(pingAnList)){
            caseDao.savePingAnCase(pingAnList);
        }
        result.setTotalCount(caseMap.size());
        result.setSucceedCount(succeedCount.get());
        result.setTotalAmount(totalAmount.get());
        return result;
    }

    private void initialCustomerInfo(PingAnCase pingAnCase,List<PhoneBean> phoneList,List<AddressBean> addressList){

        PhoneBean phone = new PhoneBean();

        // 客户手机号码
        if(StringUtils.isNotBlank(pingAnCase.getPhoneNo())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCustomerName());
            phone.setCustomerRelations(RelationEnum.ONESELF.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getPhoneNo());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 客户家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getResidentialTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCustomerName());
            phone.setCustomerRelations(RelationEnum.FAMILY.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getResidentialTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 客户单位地址电话
        if(StringUtils.isNotBlank(pingAnCase.getCompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCustomerName());
            phone.setCustomerRelations(RelationEnum.UNIT.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 直系人电话
        if(StringUtils.isNotBlank(pingAnCase.getDirectLine())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getImmediateFamilyContactName());
            phone.setCustomerRelations(pingAnCase.getCardholderRelationship());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getDirectLine());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 直系人手机
        if(StringUtils.isNotBlank(pingAnCase.getDirectLinePhone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getImmediateFamilyContactName());
            phone.setCustomerRelations(pingAnCase.getCardholderRelationship());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getDirectLinePhone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 非直系人电话
        if(StringUtils.isNotBlank(pingAnCase.getNonLinealPersonTelephone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getNonLinealPersonName());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getNonLinealPersonTelephone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 非直系人手机
        if(StringUtils.isNotBlank(pingAnCase.getNonLinealPersonPhone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getNonLinealPersonName());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getNonLinealPersonPhone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人1手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder1Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder1Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder1Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人1公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder1CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder1Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder1CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人1家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder1HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder1Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder1HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人2手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder2Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder2Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder2Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人2公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder2CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder2Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder2CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人2家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder2HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder2Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder2HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人3手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder3Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder3Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder3Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人3公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder3CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder3Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder3CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人3家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder3HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder3Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder3HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人4手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder4Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder4Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder4Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人4公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder4CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder4Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder4CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人4家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder4HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder4Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder4HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人5手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder5Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder5Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder5Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人5公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder5CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder5Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder5CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人5家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder5HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder5Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder5HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人6手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder6Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder6Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder6Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人6公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder6CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder6Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder6CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人6家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder6HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder6Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder6HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人7手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder7Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder7Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder7Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人7公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder7CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder7Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder7CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人7家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder7HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder7Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder7HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人8手机
        if(StringUtils.isNotBlank(pingAnCase.getCardholder8Phone())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder8Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.PHONE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder8Phone());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人8公司电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder8CompanyTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder8Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder8CompanyTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }
        // 卡持卡人8家庭电话
        if(StringUtils.isNotBlank(pingAnCase.getCardholder8HomeTel())){
            phone.setPartyNo(pingAnCase.getPartyNo());
            phone.setAvailable(AvailableEnum.VALID.availableDescribe());
            phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            phone.setName(pingAnCase.getCardholder8Name());
            phone.setCustomerRelations(RelationEnum.OTHER.relationDescribe());
            phone.setPhoneType(PhoneTypeEnum.LANDLINE.phoneDescribe());
            phone.setPhone(pingAnCase.getCardholder8HomeTel());
            phoneList.add(phone);
            phone = new PhoneBean();
        }

        AddressBean address = new AddressBean();

        // 客户家庭地址
        if(StringUtils.isNotBlank(pingAnCase.getResidentialAddress())){
            address.setPartyNo(pingAnCase.getPartyNo());
            address.setAvailable(AvailableEnum.VALID.availableDescribe());
            address.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            address.setAddress(pingAnCase.getResidentialAddress());
            address.setAddressType(RelationEnum.FAMILY.relationDescribe());
            addressList.add(address);
            address = new AddressBean();
        }
        // 客户单位地址
        if(StringUtils.isNotBlank(pingAnCase.getCompanyAddress())){
            address.setPartyNo(pingAnCase.getPartyNo());
            address.setAvailable(AvailableEnum.VALID.availableDescribe());
            address.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            address.setAddress(pingAnCase.getCompanyAddress());
            address.setAddressType(RelationEnum.UNIT.relationDescribe());
            addressList.add(address);
            address = new AddressBean();
        }
        // 帐单地址
        if(StringUtils.isNotBlank(pingAnCase.getBillingAddress())){
            address.setPartyNo(pingAnCase.getPartyNo());
            address.setAvailable(AvailableEnum.VALID.availableDescribe());
            address.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
            address.setAddress(pingAnCase.getBillingAddress());
            address.setAddressType(RelationEnum.BILL.relationDescribe());
            addressList.add(address);
            address = new AddressBean();
        }
    }

}
