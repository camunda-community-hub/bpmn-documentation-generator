<#import "../dmn-template-util.ftl" as util />

<#macro showLiteralExpression literalExpression="None">

    <#if literalExpression?has_content>
        <#if literalExpression != "None">
            <h3>Literal Expression</h3>
            <sup>${literalExpression.id}</sup>
            <@util.emptyOrNull literalExpression.expressionLanguage />
            <pre>
                ${literalExpression.text}
            </pre>
        </#if>
    </#if>
    
</#macro>