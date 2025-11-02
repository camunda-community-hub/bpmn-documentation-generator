<#import "../dmn-template-util.ftl" as util />

<#macro showLiteralExpression literalExpression="None">

    <#if literalExpression?has_content>
        <#if literalExpression != "None">
### Literal Expression
<sup>${literalExpression.id}</sup>

<@util.emptyOrNull literalExpression.expressionLanguage />

```feel
    ${literalExpression.text}
```
        </#if>
    </#if>
    
</#macro>