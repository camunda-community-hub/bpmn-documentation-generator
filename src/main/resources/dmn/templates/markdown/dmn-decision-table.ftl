<#import "../dmn-template-util.ftl" as util />

<#macro showDecisionTable decisionTable="None">


<#if decisionTable?has_content>
<#if decisionTable != "None">
### Decision Table

Hit Policy: ${decisionTable.hitPolicy}

<#assign headerSize = decisionTable.inputs?size + decisionTable.outputs?size />
<#assign inputs><#compress><#if decisionTable.inputs?has_content><#list decisionTable.inputs as input> <@util.emptyOrNull input.label "label" /> <@util.emptyOrNull input.inputExpression.text "name" "italic"/> (${input.inputExpression.typeRef}) |</#list></#if></#compress></#assign>
<#assign outputs><#compress><#if decisionTable.outputs?has_content><#list decisionTable.outputs as output> <@util.emptyOrNull output.label "label" /> <@util.emptyOrNull output.name "name" "italic"/> (${output.typeRef}) |</#list></#if></#compress></#assign>
| # | ${inputs?replace('\n|\r', ' ', 'r')} ${outputs?replace('\n|\r', ' ', 'r')} Annotations |
| --- |<#list 1..headerSize as i> --- |</#list> --- |
<#if decisionTable.rules?has_content>
    <#list decisionTable.rules>
        <#items as rule>
<#assign annotation><#compress><@util.emptyOrNull rule.description "" /></#compress></#assign>
| ${rule_index + 1} | <#if rule.inputEntries?has_content><#list rule.inputEntries as input>${input.text} | </#list></#if><#if rule.outputEntries?has_content><#list rule.outputEntries as output>${output.text} | </#list></#if>${annotation} |
        </#items>
    </#list>
</#if>
</#if>
</#if>
</#macro>