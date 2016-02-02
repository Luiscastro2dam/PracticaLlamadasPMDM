
window.addEventListener("load", function () {
		var chart = new CanvasJS.Chart("chartContainer",
		{
			theme: "theme3",
                        animationEnabled: true,
			title:{
				text: "LLAMADAS DE LA SEMANA",
				fontSize: 30
			},
			toolTip: {
				shared: true
			},
			axisY: {
				title: "ENTRANTES"
			},
			axisY2: {
				title: "SALIENTES"
			},
			data: [
			{
				type: "column",
				name: "Entrantes",
				legendText: "Entrantes",
				showInLegend: true,
				dataPoints:[
				{label: "Lunes", y: interfaz.datos(0,1)},
				{label: "Marte", y: interfaz.datos(0,2)},
				{label: "Miercoles", y: interfaz.datos(0,3)},
				{label: "Jueves", y: interfaz.datos(0,4)},
				{label: "Viernes", y: interfaz.datos(0,5)},
				{label: "Sabado", y: interfaz.datos(0,6)},
				{label: "Domingo", y: interfaz.datos(0,7)}



				]
			},
			{
				type: "column",
				name: "Salientes",
				legendText: "Salientes",
				axisYType: "secondary",
				showInLegend: true,
				dataPoints:[
				{label: "Lunes", y: interfaz.datos(1,1)},
				{label: "Martes", y: interfaz.datos(1,2)},
				{label: "Miercoles", y: interfaz.datos(1,3)},
				{label: "Jueves", y: interfaz.datos(1,4)},
				{label: "Viernes", y: interfaz.datos(1,5)},
				{label: "Sabado", y: interfaz.datos(1,6)},
				{label: "Domingo", y: interfaz.datos(1,7)}

				]
			}

			],
          legend:{
            cursor:"pointer",
            itemclick: function(e){
              if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
              	e.dataSeries.visible = false;
              }
              else {
                e.dataSeries.visible = true;
              }
            	chart.render();
            }
          },
        });

chart.render();

})