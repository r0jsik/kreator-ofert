package mr.record;


import java.util.List;
import java.util.function.Consumer;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.*;


public class TableRecordsView<T extends Record> extends TableView<T> implements RefreshableRecordsView<T>
{
	private Consumer<T> onSelectRequest;
	private Consumer<T> onRemoveRequest;
	
	public TableRecordsView()
	{
		setOnMouseClicked(this::handleMouseEvent);
		setOnKeyPressed(this::handleKeyEvent);
	}
	
	private void handleMouseEvent(MouseEvent event)
	{
		if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
		{
			invokeAction(onSelectRequest);
		}
		
		if (event.getButton() == MouseButton.SECONDARY)
		{
			invokeAction(onRemoveRequest);
		}
	}
	
	public void invokeAction(Consumer<T> action)
	{
		T record = getSelectionModel().getSelectedItem();
		
		if (action != null && record != null)
		{
			action.accept(record);
		}
	}
	
	private void handleKeyEvent(KeyEvent event)
	{
		if (event.getCode() == KeyCode.ENTER)
		{
			invokeAction(onSelectRequest);
		}
		
		if (event.getCode() == KeyCode.DELETE)
		{
			invokeAction(onRemoveRequest);
		}
	}
	
	public void updateColumns()
	{
		List<TableColumn<T, ?>> columns = getColumns();
		
		for (int i = 0; i < columns.size(); i++)
		{
			TableColumn<T, String> column = (TableColumn<T, String>) columns.get(i);
			
			final int index = i;
			
			column.setCellValueFactory(item -> {
				return getColumnContent(item, index);
			});
		}
	}
	
	private SimpleStringProperty getColumnContent(TableColumn.CellDataFeatures<? extends T, ?> item, int index)
	{
		return new SimpleStringProperty(item.getValue().getField(index));
	}
	
	@Override
	public void show(List<T> records)
	{
		setItems(FXCollections.observableArrayList(records));
	}
	
	@Override
	public void show(T record)
	{
		getItems().add(record);
	}
	
	@Override
	public void hide(T record)
	{
		getItems().remove(record);
	}
	
	@Override
	public void hideAll()
	{
		getItems().clear();
	}
	
	public void focus()
	{
		getSelectionModel().selectFirst();
		requestFocus();
	}
	
	public void setOnSelectRequest(Consumer<T> action)
	{
		onSelectRequest = action;
	}
	
	public void setOnRemoveRequest(Consumer<T> action)
	{
		onRemoveRequest = action;
	}
	
	@Override
	public void refresh(T record)
	{
		refresh();
	}
}