<#import "../dmn-template-util.ftl" as util/>

<#macro listKnowledgeSources knowledgeSources>

    <details ${openSections}>
        <summary><h2>Knowledge Sources</h2></summary>

        <ul>
            <#list knowledgeSources as knowledgeSource>
                <li><a href="#${knowledgeSource.id}"><@util.emptyOrNull knowledgeSource.name "knowledge source" /></a></li>
            </#list>
        </ul>

        <#list knowledgeSources as knowledgeSource>
            <details ${openSections}>
                <summary><h3 id="${knowledgeSource.id}"><@util.emptyOrNull knowledgeSource.name "knowledge source"/></h3></summary>
                <sup>(id: ${knowledgeSource.id})</sup><br/><br/>
                <#if knowledgeSource.documentation?has_content>
                    <blockquote>${knowledgeSource.documentation}</blockquote>
                <#else>
                    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false />
                </#if>
            </details>
        </#list>

    </details>

</#macro>