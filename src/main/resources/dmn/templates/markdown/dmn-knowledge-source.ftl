<#import "../dmn-template-util.ftl" as util/>

<#macro listKnowledgeSources knowledgeSources>

<#--    <details ${openSections}>-->
## Knowledge Sources

    <#list knowledgeSources as knowledgeSource>
* <a href="#${knowledgeSource.id}"><@util.emptyOrNull knowledgeSource.name "knowledge source" /></a>
    </#list>

    <#list knowledgeSources as knowledgeSource>
    <#--            <details ${openSections}>-->
### <@util.emptyOrNull knowledgeSource.name "knowledge source"/><sup>(id: ${knowledgeSource.id})</sup>
        <#if knowledgeSource.documentation?has_content>
> ${knowledgeSource.documentation}
        <#else>
<@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=true />
        </#if>
    <#--            </details>-->
    </#list>
<#--    </details> -->
</#macro>