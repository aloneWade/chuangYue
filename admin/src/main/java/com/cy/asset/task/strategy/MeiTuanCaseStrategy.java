package com.cy.asset.task.strategy;

import com.cy.asset.common.util.BeanContext;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.customer.bean.AddressBean;
import com.cy.asset.customer.bean.AvailableEnum;
import com.cy.asset.customer.bean.PhoneAttribute;
import com.cy.asset.customer.bean.PhoneBean;
import com.cy.asset.customer.bean.RelationEnum;
import com.cy.asset.customer.bean.SensitiveEnum;
import com.cy.asset.customer.dao.CustomerDao;
import com.cy.asset.customer.service.Impl.CustomerServiceImpl;
import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.CaseEnum;
import com.cy.asset.task.bean.CaseImportBean;
import com.cy.asset.customer.bean.CustomerBean;
import com.cy.asset.task.bean.CollectStatusEnum;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.dao.CaseDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wc
 * @date 2020/12/4
 */
public class MeiTuanCaseStrategy implements CaseStrategy {

    private CaseDao caseDao = BeanContext.getApplicationContext().getBean(CaseDao.class);
    private CustomerDao customerDao = BeanContext.getApplicationContext().getBean(CustomerDao.class);

    private CustomerServiceImpl customerService = BeanContext.getApplicationContext().getBean(CustomerServiceImpl.class);

    @Override
    public ResultBean generateCase(List<Map<String,Object>> caseMap, CaseImportBean caseImport) {
        ResultBean result = new ResultBean();
        // 成功案件数统计
        AtomicReference<Integer> succeedCount = new AtomicReference<>(0);
        // 案件总金额统计
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(new BigDecimal(0.00));
        List<MeiTuanCase> meiTuanCaseList = BeanToMapUtil.convertListMap2ListBean(caseMap, MeiTuanCase.class);
        // 客户信息
        List<CustomerBean> customerList = new ArrayList<>();
        // 个案信息
        List<CaseBean> caseList = new ArrayList<>();
        // 美团个案信息
        List<MeiTuanCase> meiTuanList = new ArrayList<>();
        // 客户手机信息
        List<PhoneBean> phoneList = new ArrayList<>();
        // 客户地址信息
        List<AddressBean> addressList = new ArrayList<>();
        meiTuanCaseList.forEach((meiTuanCase)->{
            CustomerBean customer = new CustomerBean();
            CaseBean caseBean = new CaseBean();
            // 将平安案件信息拷贝到客户和个案的bean属性中
            BeanUtils.copyProperties(meiTuanCase,customer);
            BeanUtils.copyProperties(meiTuanCase,caseBean);
            // 获取客户基本信息
            customerService.generateCustomerInfo(customer);
            // 客户手机和地址信息
            initialCustomerInfo(meiTuanCase,phoneList,addressList);

            caseBean.setBatchCode(caseImport.getBatchCode());
            caseBean.setCaseStatus(CollectStatusEnum.NEW_CASE.collectType());
            caseBean.setCaseSource(CaseEnum.MEI_TUAN.caseType());
            // 美团只有客户号 案件号和客户号统一
            caseBean.setPartyNo(meiTuanCase.getCaseSerialNumber());
            customer.setPartyNo(meiTuanCase.getCaseSerialNumber());

            customerList.add(customer);
            caseList.add(caseBean);
            meiTuanList.add(meiTuanCase);
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
            if( customerList.size() == 500 ){
                customerDao.saveCustomer(customerList);
                customerList.clear();
            }
            // 生成个案信息
            if( caseList.size() == 500 ){
                caseDao.saveCase(caseList);
                caseList.clear();
            }
            // 平安案件信息
            if( caseList.size() == 500 ){
                caseDao.saveMeiTuanCase(meiTuanList);
                meiTuanList.clear();
            }
            totalAmount.set(totalAmount.get().add((meiTuanCase.getAppointCaseAmount())));
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
        if(CollectionUtils.isNotEmpty(meiTuanList)){
            caseDao.saveMeiTuanCase(meiTuanList);
        }
        result.setTotalCount(caseMap.size());
        result.setSucceedCount(succeedCount.get());
        result.setTotalAmount(totalAmount.get());
        return result;
    }

    private void initialCustomerInfo(MeiTuanCase meiTuanCase, List<PhoneBean> phoneList, List<AddressBean> addressList) {

        // 低敏电话_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getLowTelephoneUnverified())){
            List<String> lowTelephoneList = Arrays.asList(meiTuanCase.getLowTelephoneUnverified().split(";"));
            lowTelephoneList.forEach(lowTelephone->{
                List<String> beanStrList = Arrays.asList(lowTelephone.split("_"));
                // 电话类型必须有 xx_138*******_手机 否则认为错误数据
                if(3 == beanStrList.size()){
                    PhoneBean phone = new PhoneBean();
                    phone.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    phone.setName(beanStrList.get(0));
                    phone.setPhone(beanStrList.get(1));
                    phone.setPhoneType(beanStrList.get(2));
                    phone.setAvailable(AvailableEnum.VALID.availableDescribe());
                    phone.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
                    phone.setCustomerRelations(RelationEnum.UNKNOWN.relationDescribe());
                    phoneList.add(phone);
                }
            });
        }
        // 中敏电话_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getMediumTelephoneUnverified())){
            List<String> mediumTelephoneList = Arrays.asList(meiTuanCase.getMediumTelephoneUnverified().split(";"));
            mediumTelephoneList.forEach(mediumTelephone->{
                List<String> beanStrList = Arrays.asList(mediumTelephone.split("_"));
                // 电话类型必须有 xx_138*******_手机 否则认为错误数据
                if(3 == beanStrList.size()){
                    PhoneBean phone = new PhoneBean();
                    phone.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    phone.setName(beanStrList.get(0));
                    phone.setPhone(beanStrList.get(1));
                    phone.setPhoneType(beanStrList.get(2));
                    phone.setAvailable(AvailableEnum.VALID.availableDescribe());
                    phone.setSensitive(SensitiveEnum.CENTRE_SENSITIVE.sensitiveDescribe());
                    phone.setCustomerRelations(RelationEnum.UNKNOWN.relationDescribe());
                    phoneList.add(phone);
                }
            });
        }
        // 高敏一级电话_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getHigh1TelephoneUnverified())){
            List<String> high1TelephoneList = Arrays.asList(meiTuanCase.getHigh1TelephoneUnverified().split(";"));
            high1TelephoneList.forEach(high1Telephone->{
                List<String> beanStrList = Arrays.asList(high1Telephone.split("_"));
                // 电话类型必须有 xx_138*******_手机 否则认为错误数据
                if(3 == beanStrList.size()){
                    PhoneBean phone = new PhoneBean();
                    phone.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    phone.setName(beanStrList.get(0));
                    phone.setPhone(beanStrList.get(1));
                    phone.setPhoneType(beanStrList.get(2));
                    phone.setAvailable(AvailableEnum.VALID.availableDescribe());
                    phone.setSensitive(SensitiveEnum.HIGH_SENSITIVE.sensitiveDescribe());
                    phone.setCustomerRelations(RelationEnum.UNKNOWN.relationDescribe());
                    phoneList.add(phone);
                }
            });
        }
        // 高敏二级电话_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getHigh2TelephoneUnverified())){
            List<String> high2TelephoneList = Arrays.asList(meiTuanCase.getHigh2TelephoneUnverified().split(";"));
            high2TelephoneList.forEach(high2Telephone->{
                List<String> beanStrList = Arrays.asList(high2Telephone.split("_"));
                // 电话类型必须有 xx_138*******_手机 否则认为错误数据
                if(3 == beanStrList.size()){
                    PhoneBean phone = new PhoneBean();
                    phone.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    phone.setName(beanStrList.get(0));
                    phone.setPhone(beanStrList.get(1));
                    phone.setPhoneType(beanStrList.get(2));
                    phone.setAvailable(AvailableEnum.VALID.availableDescribe());
                    phone.setSensitive(SensitiveEnum.HIGH_SENSITIVE.sensitiveDescribe());
                    phone.setCustomerRelations(RelationEnum.UNKNOWN.relationDescribe());
                    phoneList.add(phone);
                }
            });
        }

        // 低敏地址_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getLowAddressUnverified())){
            List<String> lowAddressList = Arrays.asList(meiTuanCase.getLowAddressUnverified().split(";"));
            lowAddressList.forEach(lowAddress->{
                List<String> beanStrList = Arrays.asList(lowAddress.split("_"));
                // 地址信息必须有 xx_家庭住址_******** 否则认为错误数据
                if(3 == beanStrList.size()){
                    AddressBean address = new AddressBean();
                    address.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    address.setName(beanStrList.get(0));
                    address.setAddressType(beanStrList.get(1));
                    address.setAddress(beanStrList.get(2));
                    address.setAvailable(AvailableEnum.VALID.availableDescribe());
                    address.setSensitive(SensitiveEnum.LOW_SENSITIVE.sensitiveDescribe());
                    addressList.add(address);
                }
            });
        }
        // 中敏地址_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getMediumAddressUnverified())){
            List<String> mediumAddressList = Arrays.asList(meiTuanCase.getMediumAddressUnverified().split(";"));
            mediumAddressList.forEach(mediumAddress->{
                List<String> beanStrList = Arrays.asList(mediumAddress.split("_"));
                // 地址信息必须有 xx_家庭住址_******** 否则认为错误数据
                if(3 == beanStrList.size()){
                    AddressBean address = new AddressBean();
                    address.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    address.setName(beanStrList.get(0));
                    address.setAddressType(beanStrList.get(1));
                    address.setAddress(beanStrList.get(2));
                    address.setAvailable(AvailableEnum.VALID.availableDescribe());
                    address.setSensitive(SensitiveEnum.CENTRE_SENSITIVE.sensitiveDescribe());
                    addressList.add(address);
                }
            });
        }
        // 高敏一级地址_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getHigh1AddressUnverified())){
            List<String> high1AddressList = Arrays.asList(meiTuanCase.getHigh1AddressUnverified().split(";"));
            high1AddressList.forEach(high1Address->{
                List<String> beanStrList = Arrays.asList(high1Address.split("_"));
                // 地址信息必须有 xx_家庭住址_******** 否则认为错误数据
                if(3 == beanStrList.size()){
                    AddressBean address = new AddressBean();
                    address.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    address.setName(beanStrList.get(0));
                    address.setAddressType(beanStrList.get(1));
                    address.setAddress(beanStrList.get(2));
                    address.setAvailable(AvailableEnum.VALID.availableDescribe());
                    address.setSensitive(SensitiveEnum.HIGH_SENSITIVE.sensitiveDescribe());
                    addressList.add(address);
                }
            });
        }
        // 高敏二级地址_未验证
        if(StringUtils.isNotBlank(meiTuanCase.getHigh2AddressUnverified())){
            List<String> high2AddressList = Arrays.asList(meiTuanCase.getHigh2AddressUnverified().split(";"));
            high2AddressList.forEach(high2Address->{
                List<String> beanStrList = Arrays.asList(high2Address.split("_"));
                // 地址信息必须有 xx_家庭住址_******** 否则认为错误数据
                if(3 == beanStrList.size()){
                    AddressBean address = new AddressBean();
                    address.setPartyNo(meiTuanCase.getCaseSerialNumber());
                    address.setName(beanStrList.get(0));
                    address.setAddressType(beanStrList.get(1));
                    address.setAddress(beanStrList.get(2));
                    address.setAvailable(AvailableEnum.VALID.availableDescribe());
                    address.setSensitive(SensitiveEnum.HIGH_SENSITIVE.sensitiveDescribe());
                    addressList.add(address);
                }
            });
        }
    }

}