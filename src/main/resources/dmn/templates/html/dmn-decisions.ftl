<#import "../dmn-template-util.ftl" as util />
<#import "dmn-extensions.ftl" as extensions />
<#import "dmn-authority-requirements.ftl" as authorityRequirements />
<#import "dmn-knowledge-requirements.ftl" as knowledgeRequirements />
<#import "dmn-information-requirements.ftl" as informationRequirements />
<#import "dmn-decision-table.ftl" as decisionTable />
<#import "dmn-literal-expression.ftl" as literalExpression />

<#macro listDecisions decisions>

    <details ${openSections}>
        <summary><h2>Decisions</h2></summary>

        <ul>
            <#list decisions as decision>
                <li><a href="#${decision.id}"><@util.emptyOrNull decision.name "decision" /></a></li>
            </#list>
        </ul>

        <#list decisions as decision>
            <details ${openSections}>
                <summary><h3 id="${decision.id}"><@util.emptyOrNull decision.name "decision"/></h3></summary>
                <sup>(id: ${decision.id})</sup><br/><br/>
                <@extensions.listExtensions decision.extensions />
                <@util.showDocumentation decision.description />
                <@decisionTable.showDecisionTable decision.decisionTable />
                <@literalExpression.showLiteralExpression decision.literalExpression />
                <@authorityRequirements.listAuthorityRequirements decision.authorityRequirements />
                <@knowledgeRequirements.listKnowledgeRequirements decision.knowledgeRequirements />
                <@informationRequirements.listInformationRequirements decision.informationRequirements />
            </details>
        </#list>

    </details>

</#macro>