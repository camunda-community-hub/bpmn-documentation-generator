<#import "../dmn-template-util.ftl" as util />
<#import "dmn-authority-requirements.ftl" as authorityRequirements />
<#import "dmn-knowledge-requirements.ftl" as knowledgeRequirements />
<#import "dmn-business-knowledge-model.ftl" as businessKnowledgeModelTable />
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
                <@businessKnowledgeModelTable.showBusinessKnowledgeModel businessKnowledgeModel />
                <@authorityRequirements.listAuthorityRequirements businessKnowledgeModel.authorityRequirements />
                <@knowledgeRequirements.listKnowledgeRequirements businessKnowledgeModel.knowledgeRequirements />
            </details>
        </#list>

    </details>

</#macro>