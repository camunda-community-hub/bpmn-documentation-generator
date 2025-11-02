<#macro listExtensions extensions>
    <#if extensions?has_content>
        <h3>Additional Information</h3>
        <#list extensions as extKey, extension>
            <#list extension as key, value>
                <p>${extKey} : ${value}</p>
            </#list>
        </#list>
    </#if>
</#macro>