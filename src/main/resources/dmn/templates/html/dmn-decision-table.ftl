<#import "../dmn-template-util.ftl" as util />

<#macro showDecisionTable decisionTable="None">

    <#if decisionTable?has_content>
        <#if decisionTable != "None">
            <h3>Decision Table</h3>
            <sup>${decisionTable.id}</sup>
            <p>Hit Policy: ${decisionTable.hitPolicy}</p>
            <table>
                <tr>
                    <th>#</th>
                    <#if decisionTable.inputs?has_content>
                        <#list decisionTable.inputs as input>
                            <th>
                                <@util.emptyOrNull input.label "" />
                                <br/>
                                <sup>${input.id}</sup>
                                <p><@util.emptyOrNull input.inputExpression.text "expression" /></p>
                                <sup>${input.inputExpression.id}</sup>
                                <p>${input.inputExpression.typeRef}</p>
                            </th>
                        </#list>
                    </#if>
                    <#if decisionTable.outputs?has_content>
                        <#list decisionTable.outputs as output>
                            <th>
                                <@util.emptyOrNull output.label "" />
                                <br/>
                                <@util.emptyOrNull output.name "name" />
                                <sup>${output.id}</sup>
                                <p>${output.typeRef}</p>
                            </th>
                        </#list>
                    </#if>
                    <th>Annotations</th>
                </tr>
                <#if decisionTable.rules?has_content>
                    <#list decisionTable.rules>
                        <#items as rule>
                            <tr>
                                <td>${rule_index + 1}</td>
                                <#if rule.inputEntries?has_content>
                                    <#list rule.inputEntries as input>
                                        <td>${input.text}</td>
                                    </#list>
                                </#if>
                                <#if rule.outputEntries?has_content>
                                    <#list rule.outputEntries as output>
                                        <td>${output.text}</td>
                                    </#list>
                                </#if>
                                <td><@util.emptyOrNull rule.description "" /></td>
                            </tr>
                        </#items>
                    </#list>
                </#if>
            </table>
        </#if>
    </#if>
</#macro>