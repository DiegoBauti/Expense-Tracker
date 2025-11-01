# Expense Tracker

Una aplicación simple de línea de comandos para gestionar tus finanzas personales.
Permite **registrar, editar, eliminar y visualizar gastos**, así como generar **resúmenes mensuales** y totales.

## Descripción

**Expense Tracker** es una herramienta que te ayuda a llevar un control básico de tus gastos diarios desde la terminal.
Ideal para practicar lógica de programación, manejo de archivos y diseño de aplicaciones CLI.

## Funcionalidades principales

*  **Agregar gastos** con descripción y monto.
*  **Actualizar gastos** existentes.
*  **Eliminar gastos** por ID.
*  **Listar todos los gastos registrados.**
*  **Mostrar un resumen general** del total de gastos.
*  **Ver un resumen mensual** (por mes del año actual).

## Ejecución

1. **Compila todo el proyecto:**

   ```bash
   javac *.java
   ```

2. **Ejecuta el programa principal:**

   ```bash
   $ java ExpenseTracker add --description "Almuerzo" --amount 20
   # Gasto agregado correctamente (ID: 1)
   
   $ java ExpenseTracker add --description "Cena" --amount 10
   # Gasto agregado correctamente (ID: 2)
   
   $ java ExpenseTracker list
   # ID  Fecha       Descripción  Monto
   # 1   2024-08-06  Almuerzo     $20
   # 2   2024-08-06  Cena         $10
   
   $ java ExpenseTracker summary
   # Total de gastos: $30
   
   $ java ExpenseTracker delete --id 2
   # Gasto eliminado correctamente
   
   $ java ExpenseTracker summary
   # Total de gastos: $20
   
   $ java ExpenseTracker summary --month 8
   # Total de gastos para agosto: $20
   ```

