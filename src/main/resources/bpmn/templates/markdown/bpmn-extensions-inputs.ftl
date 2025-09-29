<#macro listInput input>

<#if input?has_content>
#### Input
    <#list input as key, value>
* **${key}:** ${value}
    </#list>
<#else>
No input defined.
</#if>

</#macro>