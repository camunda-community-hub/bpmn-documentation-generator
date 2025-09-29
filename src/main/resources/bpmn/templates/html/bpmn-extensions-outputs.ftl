<#macro listOutput outputs>

    <#if outputs?has_content>
        <h6>Outputs</h6>
        <table>
            <thead>
            <#list outputs[0] as outputKey, outputValue>
                <tr>
                    <#list outputValue as key, value>
                        <th>${key?capitalize}</th>
                    </#list>
                </tr>
            </#list>
            </thead>
            <tbody>
            <#list outputs as output>
                <#list output as outputKey, outputValue>
                    <tr>
                        <#list outputValue as key, value>
                            <td>${value}</td>
                        </#list>
                    </tr>
                </#list>
            </#list>
            </tbody>
        </table>
    <#else>
        <@util.emptySection skip=skipEmptySections section="outputs defined" quote=false markdown=false/>
    </#if>

</#macro>