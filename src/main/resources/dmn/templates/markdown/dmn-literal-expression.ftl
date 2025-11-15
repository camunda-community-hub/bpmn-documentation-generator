<#import "../dmn-template-util.ftl" as util />

<#macro showLiteralExpression literalExpression="None">

    <#if literalExpression?has_content>
        <#if literalExpression != "None">
<a id="${literalExpression.id}"></a>
### Literal Expression

<@util.emptyOrNull literalExpression.expressionLanguage />

```feel
    ${literalExpression.text}
```
        </#if>
    </#if>
    
</#macro>