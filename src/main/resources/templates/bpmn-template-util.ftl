<#macro emptyOrNull variable="Unnamed" type="element">
    <#if variable?has_content>
        <#if variable = "Unnamed">
            ${variable} ${type}
        <#else>
            ${variable}
        </#if>
    <#else>
        &lt;Empty&gt; ${type}
    </#if>
</#macro>