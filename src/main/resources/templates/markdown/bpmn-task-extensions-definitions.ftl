<#macro listDefinition definition>

<#if definition?has_content>

#### Task Definition
    <#list definition as key, value>
* **${key}:** ${value}
    </#list>
<#else>
No definition defined.
</#if>

</#macro>