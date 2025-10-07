<#import "../dmn-template-util.ftl" as util/>

<#macro listInputData inputdata>

    <details ${openSections}>
        <summary><h2>Input Data</h2></summary>

        <ul>
            <#list inputdata as input>
                <li><a href="#${input.id}"><@util.emptyOrNull input.name "input data" /></a></li>
            </#list>
        </ul>

        <#list inputdata as input>
            <details ${openSections}>
                <summary><h3 id="${input.id}"><@util.emptyOrNull input.name "input data"/></h3></summary>
                <sup>(id: ${input.id})</sup><br/><br/>
                <#if input.documentation?has_content>
                    <blockquote>${input.documentation}</blockquote>
                <#else>
                    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false />
                </#if>
            </details>
        </#list>

    </details>

</#macro>