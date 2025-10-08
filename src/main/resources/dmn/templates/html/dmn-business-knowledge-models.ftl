<#import "../dmn-template-util.ftl" as util/>

<#macro listBusinessKnowledgeModels businessKnowledgeModels>

    <details ${openSections}>
        <summary><h2>Business Knowledge Models</h2></summary>

        <ul>
            <#list businessKnowledgeModels as businessKnowledgeModel>
                <li><a href="#${businessKnowledgeModel.id}"><@util.emptyOrNull businessKnowledgeModel.name "business knowledge model" /></a></li>
            </#list>
        </ul>

        <#list businessKnowledgeModels as businessKnowledgeModel>
            <details ${openSections}>
                <summary><h3 id="${businessKnowledgeModel.id}"><@util.emptyOrNull businessKnowledgeModel.name "business knowledge model"/></h3></summary>
                <sup>(id: ${businessKnowledgeModel.id})</sup><br/><br/>
                <#if businessKnowledgeModel.documentation?has_content>
                    <blockquote>${businessKnowledgeModel.documentation}</blockquote>
                <#else>
                    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false />
                </#if>
            </details>
        </#list>

    </details>

</#macro>