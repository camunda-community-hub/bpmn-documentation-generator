<#macro listInput inputs>

    <#if inputs?has_content>
        <details>
            <summary><h4>Inputs</h4></summary>
            <table>
                <thead>
                <#list inputs[0] as inputKey, inputValue>
                    <tr>
                        <#list inputValue as key, value>
                            <th>${key?capitalize}</th>
                        </#list>
                    </tr>
                </#list>
                </thead>
                <tbody>
                <#list inputs as input>
                    <#list input as inputKey, inputValue>
                        <tr>
                            <#list inputValue as key, value>
                                <td>${value}</td>
                            </#list>
                        </tr>
                    </#list>
                </#list>
                </tbody>
            </table>
        </details>
    <#else>
        <p>No inputs defined.</p>
    </#if>

</#macro>