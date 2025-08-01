<#macro listOutput output>

<#if output?has_content>
#### Output
    <#list output as key, value>
* **${key}:** ${value}
    </#list>
<#else>
    <p>No output defined.</p>
</#if>

</#macro>