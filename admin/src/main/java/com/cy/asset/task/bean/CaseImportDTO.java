package com.cy.asset.task.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wc
 * @date 2020/12/14
 * @describe 导入案件参数
 */
@Data
public class CaseImportDTO {

    /** 文件路径 **/
    @NotBlank(message = "文件路径不能为空")
    private String filePath;
    /** 批次号 **/
    @NotBlank(message = "批次号不能为空")
    private String batchCode;
    /** 产品名称 **/
    @NotBlank(message = "产品名称不能为空")
    private String productName;
    /** 案件类型 **/
    private CaseEnum caseEnum;

}
