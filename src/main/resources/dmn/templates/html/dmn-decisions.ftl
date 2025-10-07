<#import "../dmn-template-util.ftl" as util/>

<#macro listDecisions decisions>

    <details ${openSections}>
        <summary><h2>Decisions</h2></summary>

        <ul>
            <#list decisions as decision>
                <li><a href="#${decision.id}"><@util.emptyOrNull decision.name "decision" /></a></li>
            </#list>
        </ul>

        <#list decisions as decision>
            <details ${openSections}>
                <summary><h3 id="${decision.id}"><@util.emptyOrNull decision.name "decision"/></h3></summary>
                <sup>(id: ${decision.id})</sup><br/><br/>
                <#if decision.documentation?has_content>
                    <blockquote>${decision.documentation}</blockquote>
                <#else>
                    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false />
                </#if>
            </details>
        </#list>

    </details>

</#macro>