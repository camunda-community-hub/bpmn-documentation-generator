<#macro listProperties properties>

<#if properties?has_content>
#### Properties
    <#list properties as key, value>
* **${key}:** ${value}
    </#list>
<#else>
No properties defined.
</#if>

</#macro>