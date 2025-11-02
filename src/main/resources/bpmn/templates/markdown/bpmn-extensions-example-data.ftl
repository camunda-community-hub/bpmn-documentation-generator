<#macro listExampleData exampleData>

    <#if exampleData?has_content>
###### Example Data

```json
    <#list exampleData as input>
        <#list input as inputKey, inputValue>
            <#list inputValue as key, value>
                <#if value != "camundaModeler:exampleOutputJson">
${value}
                </#if>
            </#list>
        </#list>
    </#list>
```
    <#else>
<@util.emptySection skip=skipEmptySections section="example data" quote=false />
    </#if>

</#macro>