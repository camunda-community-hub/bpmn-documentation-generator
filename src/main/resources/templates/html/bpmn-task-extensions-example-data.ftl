<#macro listExampleData exampleData>

    <#if exampleData?has_content>
        <details>
            <summary><h4>Example Data</h4></summary>
            <ul>
                <#list exampleData as key, value>
                    <li><strong>${key}:</strong> ${value}</li>
                </#list>
            </ul>
        </details>
    <#else>
        <p>No example data defined.</p>
    </#if>

</#macro>