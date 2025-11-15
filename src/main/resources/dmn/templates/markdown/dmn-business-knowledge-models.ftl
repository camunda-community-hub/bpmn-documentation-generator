<#import "../dmn-template-util.ftl" as util />
<#import "dmn-authority-requirements.ftl" as authorityRequirements />
<#import "dmn-knowledge-requirements.ftl" as knowledgeRequirements />
<#import "dmn-business-knowledge-model.ftl" as businessKnowledgeModelTable />

<#macro listbBusinessKnowledgeModels businessKnowledgeModels>

## Business Knowledge Models

    <#list businessKnowledgeModels as businessKnowledgeModel>
<#assign businessKnowledgeModelName><#compress><@util.emptyOrNull businessKnowledgeModel.name "business knowledge model" /></#compress></#assign>
* [${businessKnowledgeModelName?trim}](#${businessKnowledgeModel.id})
    </#list>

    <#list businessKnowledgeModels as businessKnowledgeModel>
<a id="${businessKnowledgeModel.id}"></a>
<#assign businessKnowledgeModelName><#compress><@util.emptyOrNull businessKnowledgeModel.name "business knowledge model" /></#compress></#assign>
### ${businessKnowledgeModelName?trim}
<@businessKnowledgeModelTable.showBusinessKnowledgeModel businessKnowledgeModel />
<@authorityRequirements.listAuthorityRequirements businessKnowledgeModel.authorityRequirements />
<@knowledgeRequirements.listKnowledgeRequirements businessKnowledgeModel.knowledgeRequirements />
    </#list>

</#macro>