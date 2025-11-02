<#import "../dmn-template-util.ftl" as util />

<#macro showDecisionTable decisionTable="None">


<#if decisionTable?has_content>
<#if decisionTable != "None">
### Decision Table
<sup>${decisionTable.id}</sup><br />
Hit Policy: ${decisionTable.hitPolicy}<br />
<#assign headerSize = decisionTable.inputs?size + decisionTable.outputs?size />
<#compress>
| # |
<#if decisionTable.inputs?has_content>
    <#list decisionTable.inputs as input>
<@util.emptyOrNull input.label "" /><br />
<sup>${input.id}</sup><br />
<@util.emptyOrNull input.inputExpression.text "expression" /><br />
<sup>${input.inputExpression.id}</sup><br />
${input.inputExpression.typeRef}<br />
|
    </#list>
</#if>
<#if decisionTable.outputs?has_content>
    <#list decisionTable.outputs as output>
<@util.emptyOrNull output.label "" /><br />
<@util.emptyOrNull output.name "name" /><br />
<sup>${output.id}</sup><br />
${output.typeRef}<br />
|
    </#list>
    </#if>
 Annotations |<br />
| --- |
    <#list 1..headerSize as i>
--- |
    </#list>
--- |<br />
<#if decisionTable.rules?has_content>
    <#list decisionTable.rules>
        <#items as rule>
| ${rule_index + 1} |
            <#if rule.inputEntries?has_content>
                <#list rule.inputEntries as input>
${input.text} |
                </#list>
            </#if>
            <#if rule.outputEntries?has_content>
                <#list rule.outputEntries as output>
${output.text} |
                </#list>
            </#if>
<@util.emptyOrNull rule.description "" /> |<br />
        </#items>
    </#list>
</#if>
</#compress>
</#if>
</#if>
</#macro>