<#import "../dmn-template-util.ftl" as util />
<#import "dmn-authority-requirements.ftl" as authorityRequirements />
<#import "dmn-knowledge-requirements.ftl" as knowledgeRequirements />
<#import "dmn-business-knowledge-model.ftl" as businessKnowledgeModelTable />

<#macro listbBusinessKnowledgeModels businessKnowledgeModels>

<#--    <details ${openSections}>-->
## Business Knowledge Models

    <#list businessKnowledgeModels as businessKnowledgeModel>
* <a href="#${businessKnowledgeModel.id}"><@util.emptyOrNull businessKnowledgeModel.name "business knowledge model" /></a>
    </#list>

    <#list businessKnowledgeModels as businessKnowledgeModel>
    <#--            <details ${openSections}>-->
### <@util.emptyOrNull businessKnowledgeModel.name "business knowledge model"/><sup>(id: ${businessKnowledgeModel.id})</sup>
<@businessKnowledgeModelTable.showBusinessKnowledgeModel businessKnowledgeModel />
<@authorityRequirements.listAuthorityRequirements businessKnowledgeModel.authorityRequirements />
<@knowledgeRequirements.listKnowledgeRequirements businessKnowledgeModel.knowledgeRequirements />
    <#--            </details>-->
    </#list>
<#--    </details> -->
</#macro>