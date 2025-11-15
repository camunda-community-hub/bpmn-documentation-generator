<#import "../dmn-template-util.ftl" as util />

<#macro showBusinessKnowledgeModel businessKnowledgeModel="None">

    <#if businessKnowledgeModel?has_content>
        <#if businessKnowledgeModel != "None">
<@util.showDocumentation businessKnowledgeModel.description />
| |
|---|
            <#if businessKnowledgeModel.encapsulatedLogic?has_content>
| F <#if businessKnowledgeModel.encapsulatedLogic.formalParameters?has_content>(<#list businessKnowledgeModel.encapsulatedLogic.formalParameters as parameter> ${parameter.name}: ${parameter.typeRef}<#if parameter_index < businessKnowledgeModel.encapsulatedLogic.formalParameters?size - 1>, </#if></#list>)<#else>(no parameters)</#if> |
                <#if businessKnowledgeModel.encapsulatedLogic.literalExpression?has_content>
                    <#if businessKnowledgeModel.encapsulatedLogic.literalExpression.text?has_content>
| ```${businessKnowledgeModel.encapsulatedLogic.literalExpression.text}``` |
                    <#else>
| ```No expression```|
                    </#if>
                </#if>
            </#if>
            <#if businessKnowledgeModel.variable?has_content>
| **Result** |
<#assign variableName><#compress><@util.emptyOrNull businessKnowledgeModel.variable.name /></#compress></#assign>
<#assign variableType><#compress><@util.emptyOrNull businessKnowledgeModel.variable.typeRef /></#compress></#assign>
| Variable name : ${variableName} |
| Result type : ${variableType} |
            <#else>
| No result |
            </#if>
        </#if>
    </#if>
</#macro>