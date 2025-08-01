<#macro emptyOrNull variable="Unnamed" type="element">
    <#if variable?has_content>
        <#if variable = "Unnamed">
            <#switch type>
                <#on "template">
                    No template attached
                <#on "template version">
                    n/a
                <#default>
                    ${variable} ${type}
            </#switch>
        <#else>
            ${variable}
        </#if>
    <#else>
        &lt;Empty&gt; ${type}
    </#if>
</#macro>