package mr.warning;


import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import mr.clients.service.ClientsServiceException;
import mr.output.excel.document.DocumentCreationException;
import mr.output.excel.form.InvalidFormContentException;
import mr.output.export.ExportException;
import mr.price.list.loader.PriceListLoadException;
import mr.settings.preferences.PreferencesUpdateException;


@Aspect
public class WarningAspect
{
	private final Warning warning;
	
	public WarningAspect(Warning warning)
	{
		this.warning = warning;
	}
	
	@AfterThrowing(pointcut = "execution(* mr.price.list.dialog.PriceListDialogMediator.selectPriceList(..))", throwing = "exception")
	private void unableToLoadPriceList(PriceListLoadException exception)
	{
		warning.show("Nie można załadować cennika", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.clients.service.ObservedClientsService.insert(..))", throwing = "exception")
	private void unableToInsertClient(ClientsServiceException exception)
	{
		warning.show("Nie można utworzyć klienta", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.clients.service.ObservedClientsService.update(..))", throwing = "exception")
	private void unableToUpdateClient(ClientsServiceException exception)
	{
		warning.show("Nie można zaktualizować klienta", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.clients.service.ObservedClientsService.remove(..))", throwing = "exception")
	private void unableToRemoveClient(ClientsServiceException exception)
	{
		warning.show("Nie można usunąć klienta", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.output.excel.form.dialog.ExcelFormDialog.show(..))", throwing = "exception")
	private void invalidFormContent(InvalidFormContentException exception)
	{
		warning.show("Formularz nie jest wypełniony prawidłowo", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.output.excel.document.DocumentGenerator.generate(..))", throwing = "exception")
	private void unableToCreateDocument(DocumentCreationException exception)
	{
		warning.show("Nie można utworzyć dokumentu", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.output.export.FileExporter.export(..))", throwing = "exception")
	private void unableToExportFile(ExportException exception)
	{
		warning.show("Nie można zapisać pliku", exception);
	}
	
	@AfterThrowing(pointcut = "execution(* mr.settings.preferences.Preferences.save(..))", throwing = "exception")
	private void unableToSavePreferences(PreferencesUpdateException exception)
	{
		warning.show("Nie można zapisać ustawień", exception);
	}
}