<#macro listInput inputs>

    <#if inputs?has_content>
        <h6>Inputs</h6>
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
    <#else>
        <@util.emptySection skip=skipEmptySections section="inputs defined" quote=false markdown=false/>
    </#if>

</#macro>