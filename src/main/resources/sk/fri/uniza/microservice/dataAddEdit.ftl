<#-- @ftlvariable name="" type="sk.fri.uniza.microservice.Data" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <#if !(data??)>
              <#assign data = {"id":-1,"date":"00:00","temp":20,"hum":80}> 
            </#if>
            <title>Add new Data</title>
            <link rel="stylesheet" type="text/css" href="/assets/view.css" media="all">
                <script type="text/javascript" src="/assets/view.js"></script>
                </head>
                <body id="main_body" >

                    <img id="top" src="/assets/top.png" alt="">
                        <div id="form_container">

                            <h1><a>Form</a></h1>
                             <#if data.id != -1>
                                <form id="form_9436" class="appnitro"  method="post" action=".">
                             <#else>
                                <form id="form_9436" class="appnitro" method="post" action="./edit">
                             </#if>
                                    <div class="form_description">
                                    <#if data.id != -1>
                                        <h2>Edit new data</h2>
                                    <#else>
                                        <h2>Add new data</h2>
                                    </#if>
                                        <p></p>
                                        </div>						
                                    <ul >
                                        <div>
                                            <input name="id" class="element text medium" type="hidden" maxlength="255" value="${data.id}"/> 
                                            </div>

                                        <li id="li_1" >
                                            <label class="description" for="element_1">Date </label>
                                            <div>
                                                <input name="date" class="element text medium" type="text" maxlength="255" value="${data.date}"/> 
                                                </div> 
                                            </li>

                                        <li id="li_2" >
                                            <label class="description" for="element_2">Temperature </label>
                                            <div>
                                                <input name="temp" class="element medium" type="number" value="${data.temp}"/> 
                                                </div> 
                                            </li>

                                        <li id="li_3" >
                                            <label class="description" for="element_3">humidity </label>
                                            <div>
                                                <input name="hum" class="element medium" type="number"  value="${data.hum}"/> 
                                                </div> 
                                            </li>
                                        <li class="buttons">
                                            <input type="hidden" name="form_id" value="9436" />
                                            <input id="saveForm" class="button_text" type="submit" name="submit" value="Submit" />
                                            </li>
                                        </ul>
                                    </form>	
                            </div>
                        <img id="bottom" src="/assets/bottom.png" alt="">
                            </body>
                            </html>