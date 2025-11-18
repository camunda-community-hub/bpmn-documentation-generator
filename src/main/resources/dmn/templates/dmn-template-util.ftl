<#macro emptyOrNull variable="Unnamed" type="element" style="normal">

    <#if variable?has_content>
        <#if variable = "Unnamed">
            <#switch type>
                <#on "">
                    <#assign str = "" />
                <#on "template">
                    <#assign str = "No template attached" />
                <#on "template version">
                    <#assign str="n/a" />
                <#default>
                    <#assign str="${variable} ${type}" />
            </#switch>
        <#else>
            <#assign str="${variable}" />
        </#if>
    <#else>
        <#assign str="&lt;Empty&gt ${type}" />
    </#if>

    <#switch style>
        <#on "bold">
            <#if markdown>
**${str}**
            <#else>
                <strong>${str}</strong>
            </#if>
        <#on "italic">
            <#if markdown>
_${str}_
            <#else>
                <em>${str}</em>
            </#if>
        <#on "normal">
${str}
    </#switch>

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