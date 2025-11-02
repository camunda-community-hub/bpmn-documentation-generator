<#macro listExtensions extensions>
    <#if extensions?has_content>
### Additional Information
        <#list extensions as extKey, extension>
            <#list extension as key, value>
* ${extKey} : ${value}</p>
            </#list>
        </#list>
    </#if>
</#macro>