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
* <a href="#${decision.id}"><@util.emptyOrNull decision.name "decision" /></a>
</#list>

<#list decisions as decision>
<#--            <details ${openSections}>-->
### <@util.emptyOrNull decision.name "decision"/><sup>(id: ${decision.id})</sup>
<@util.showDocumentation decision.description />
<@decisionTable.showDecisionTable decision.decisionTable />
<@literalExpression.showLiteralExpression decision.literalExpression />
<@authorityRequirements.listAuthorityRequirements decision.authorityRequirements />
<@knowledgeRequirements.listKnowledgeRequirements decision.knowledgeRequirements />
<@informationRequirements.listInformationRequirements decision.informationRequirements />
<#--            </details>-->
</#list>
<#--    </details> -->
</#macro>