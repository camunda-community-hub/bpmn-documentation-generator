<#import "../dmn-template-util.ftl" as util/>

<#macro listbBusinessKnowledgeModels businessKnowledgeModels>

<#--    <details ${openSections}>-->
## Business Knowledge Models

    <#list businessKnowledgeModels as businessKnowledgeModel>
* <a href="#${businessKnowledgeModel.id}"><@util.emptyOrNull businessKnowledgeModel.name "business knowledge model" /></a>
    </#list>

    <#list businessKnowledgeModels as businessKnowledgeModel>
    <#--            <details ${openSections}>-->
### <@util.emptyOrNull businessKnowledgeModel.name "business knowledge model"/><sup>(id: ${businessKnowledgeModel.id})</sup>
        <#if businessKnowledgeModel.documentation?has_content>
            > ${businessKnowledgeModel.documentation}
        <#else>
            <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=true />
        </#if>
    <#--            </details>-->
    </#list>
<#--    </details> -->
</#macro>