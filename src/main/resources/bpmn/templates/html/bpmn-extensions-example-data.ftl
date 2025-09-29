<#macro listExampleData exampleData>

    <#if exampleData?has_content>
        <h6>Example Data</h6>
        <pre>
            <#list exampleData as input>
                <#list input as inputKey, inputValue>
                        <#list inputValue as key, value>
                            <#if value != "camundaModeler:exampleOutputJson">
                                <code>${value}</code>
                            </#if>
                        </#list>
                </#list>
            </#list>
        </pre>
    <#else>
        <@util.emptySection skip=skipEmptySections section="example data" quote=false markdown=false/>
    </#if>

</#macro>