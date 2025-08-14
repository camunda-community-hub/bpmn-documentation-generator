<#macro listExampleData exampleData>

    <#if exampleData?has_content>
#### Example Data

        <#list exampleData as key, value>
```json
${value}
```
        </#list>
    <#else>
No example data defined.
    </#if>

</#macro>