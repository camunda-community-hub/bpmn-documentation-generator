<#import "../dmn-template-util.ftl" as util/>
<#import "dmn-authority-requirements.ftl" as authorityRequirements />

<#macro listKnowledgeSources knowledgeSources>

## Knowledge Sources

    <#list knowledgeSources as knowledgeSource>
<#assign knowledgeSourceName><#compress><@util.emptyOrNull knowledgeSource.name "knowledge source" /></#compress></#assign>
* [${knowledgeSourceName?trim}](#${knowledgeSource.id})
    </#list>

    <#list knowledgeSources as knowledgeSource>
<#assign knowledgeSourceName><#compress><@util.emptyOrNull knowledgeSource.name "knowledge source" /></#compress></#assign>
<a id="${knowledgeSource.id}"></a>
### ${knowledgeSourceName?trim}
<@util.showDocumentation knowledgeSource.description />
<@authorityRequirements.listAuthorityRequirements knowledgeSource.authorityRequirements />
    </#list>

</#macro>