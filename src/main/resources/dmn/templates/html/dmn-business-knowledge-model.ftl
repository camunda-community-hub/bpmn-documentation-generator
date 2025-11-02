<#import "../dmn-template-util.ftl" as util />

<#macro showBusinessKnowledgeModel businessKnowledgeModel="None">
    <#if businessKnowledgeModel?has_content>
        <#if businessKnowledgeModel != "None">
            <table>
                <tr>
                    <th colspan="2">${businessKnowledgeModel.name}</th>
                </tr>
                <tr>
                    <td colspan="2">
                        <@util.showDocumentation businessKnowledgeModel.description />
                    </td>
                </tr>
                <#if businessKnowledgeModel.encapsulatedLogic?has_content>
                    <tr>
                        <td style="width: fit-content;">F</td>
                        <#if businessKnowledgeModel.encapsulatedLogic.formalParameters?has_content>
                            <td>(
                                <#list businessKnowledgeModel.encapsulatedLogic.formalParameters as parameter>
                                    ${parameter.name}: ${parameter.typeRef}<#if parameter_index < businessKnowledgeModel.encapsulatedLogic.formalParameters?size - 1>, </#if>
                                </#list>
                                )
                            </td>
                        <#else>
                            <td>No parameters</td>
                        </#if>
                    </tr>
                    <tr>
                        <td colspan="2" text-align="left">
                            <#if businessKnowledgeModel.encapsulatedLogic.literalExpression?has_content>
                                <#if businessKnowledgeModel.encapsulatedLogic.literalExpression.text?has_content>
                                    ${businessKnowledgeModel.encapsulatedLogic.literalExpression.text}
                                </#if>
                            <#else>
                                <pre>No expression</pre>
                            </#if>
                        </td>
                    </tr>
                </#if>
                <tr>
                    <td colspan="2">
                        <#if businessKnowledgeModel.variable?has_content>
                            <tr><td colspan="2">Result</td></tr>
                            <tr><td>Variable name</td><td>Result type</td></tr>
                            <tr><td><@util.emptyOrNull businessKnowledgeModel.variable.name /></td><td><@util.emptyOrNull businessKnowledgeModel.variable.typeRef /></td></tr>
                        <#else>
                            No result
                        </#if>
                    </td>
                </tr>
            </table>
        </#if>
    </#if>

</#macro>