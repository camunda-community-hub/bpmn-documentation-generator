<#import "../dmn-template-util.ftl" as util/>
<#import "dmn-authority-requirements.ftl" as authorityRequirements />
<#import "dmn-knowledge-requirements.ftl" as knowledgeRequirements/>
<#import "dmn-information-requirements.ftl" as informationRequirements/>
<#import "dmn-decision-table.ftl" as decisionTable />
<#import "dmn-literal-expression.ftl" as literalExpression />

<#macro listDecisions decisions>

<#--    <details ${openSections}>-->
## Decisions

<#list decisions as decision>
<#assign decisionName><#compress><@util.emptyOrNull decision.name "decision"/></#compress></#assign>
* [${decisionName?trim}](#${decision.id})
</#list>

<#list decisions as decision>
<#assign decisionName><#compress><@util.emptyOrNull decision.name "decision"/></#compress></#assign>
<a id="${decision.id}"></a>
### ${decisionName?trim}
<@util.showDocumentation decision.description />
<@decisionTable.showDecisionTable decision.decisionTable />
<@literalExpression.showLiteralExpression decision.literalExpression />
<@authorityRequirements.listAuthorityRequirements decision.authorityRequirements />
<@knowledgeRequirements.listKnowledgeRequirements decision.knowledgeRequirements />
<@informationRequirements.listInformationRequirements decision.informationRequirements />
</#list>

</#macro>