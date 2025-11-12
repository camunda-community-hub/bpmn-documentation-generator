<#macro emptyOrNull variable="Unnamed" type="element">
    <#if variable?has_content>
        <#if variable = "Unnamed">
            <#switch type>
<#on "template">No template attached
<#on "template version">n/a
<#default>
${variable?trim} ${type}
            </#switch>
<#else>
${variable?trim}
</#if>
<#else>
&lt;Empty&gt; ${type}
</#if>

</#macro>

<#macro emptySection skip section="content" quote=true>
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

<#macro showDocumentation documentation="Empty" section="documentation" quote=true>

    <#if documentation?has_content>
        <#if documentation != "Empty">
            <#if markdown>
                <#if quote>
> ${documentation}
                <#else>
${documentation}
                </#if>
            <#else>
                <#if quote>
                    <blockquote>
                        ${documentation}
                    </blockquote>
                <#else>
                    <p>
                        ${documentation}
                    </p>
                </#if>
            </#if>
        <#else>
            <@emptySection skip=skipEmptySections section=section quote=quote />
        </#if>
    </#if>

</#macro>