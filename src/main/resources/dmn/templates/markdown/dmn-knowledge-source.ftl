<#import "../dmn-template-util.ftl" as util/>
<#import "dmn-authority-requirements.ftl" as authorityRequirements />

<#macro listKnowledgeSources knowledgeSources>

<#--    <details ${openSections}>-->
## Knowledge Sources

    <#list knowledgeSources as knowledgeSource>
* <a href="#${knowledgeSource.id}"><@util.emptyOrNull knowledgeSource.name "knowledge source" /></a>
    </#list>

    <#list knowledgeSources as knowledgeSource>
    <#--            <details ${openSections}>-->
### <@util.emptyOrNull knowledgeSource.name "knowledge source"/><sup>(id: ${knowledgeSource.id})</sup>
<@util.showDocumentation knowledgeSource.description />
<@authorityRequirements.listAuthorityRequirements knowledgeSource.authorityRequirements />
    <#--            </details>-->
    </#list>
<#--    </details> -->
</#macro>