<#import "../bpmn-template-util.ftl" as util/>

<#macro listTaskDefinition taskDefinition>

    <#if taskDefinition?has_content>
###### Task Definition
| Key | Value |
| --- | ----- |
        <#list taskDefinition as key, value>
| ${key?capitalize} | ${value} |
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="task definition" quote=false />
    </#if>

</#macro>

