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

<#macro emptySection skip section="content" quote=true markdown=false>
    <#if !skip>
        <#if markdown>
            <#if quote>
> No ${section}
            <#else>
No ${section}
            </#if>
        <#else>
            <#if quote>
                <blockquote>No ${section}</blockquote>
            <#else>
                <p>No ${section}</p>
            </#if>
        </#if>
    </#if>
</#macro>