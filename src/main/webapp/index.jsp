<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Book Web Access</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link href="bookStoreAppCSS.css" rel="stylesheet" type="text/css"/>
        <link href="bgrins-spectrum-98454b5/spectrum.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="indexBody">
        <div class="bodyItems">
            <h1>Book Web Access Index Page</h1>

            <br>
            <form method="Post" action="<%= response.encodeURL("AuthorController?task=color")%>">
                <input type='text' id="TableColor" name="TableColor" value="white" />
                <input type='text' id="TextColor" name="TextColor" value="black" />
                <input type="submit" value="List of Authors">
            </form>




        </div>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="bgrins-spectrum-98454b5/spectrum.js" type="text/javascript"></script>
        <script>
            $("#TableColor").spectrum({
                showPaletteOnly: true,
                showPalette: true,
                color: 'white',
                change: function (color) {
                    $('#TableColor').val(color);
                }, palette: [
                    ['black', 'white', 'blanchedalmond',
                        'rgb(255, 128, 0);', 'hsv 100 70 50'],
                    ['red', 'yellow', 'green', 'blue', 'violet']
                ]

            });

            $("#TextColor").spectrum({
                showPaletteOnly: true,
                showPalette: true,
                color: 'black',
                change: function (color2) {
                    $('#TextColor').val(color2);
                },
                palette: [
                    ['black', 'white', 'blanchedalmond',
                        'rgb(255, 128, 0);', 'hsv 100 70 50'],
                    ['red', 'yellow', 'green', 'blue', 'violet']
                ]
            });	

$( document ).ready(function() {
  $("#TableColor").spectrum("set", $("#TableColor").val());
  $("#TextColor").spectrum("set", $("#TextColor").val());
});

        </script>
    </body>
</html>
