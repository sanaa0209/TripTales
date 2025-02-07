package com.unimib.triptales.source.expense;

import com.unimib.triptales.R;
import com.unimib.triptales.database.ExpenseDao;
import com.unimib.triptales.model.Expense;

import java.util.List;

public class ExpenseLocalDataSource extends BaseExpenseLocalDataSource {

    private final ExpenseDao expenseDao;
    private final String diaryId;

    public ExpenseLocalDataSource(ExpenseDao expenseDao, String diaryId) {
        this.expenseDao = expenseDao;
        this.diaryId = diaryId;
    }

    @Override
    public void insertExpense(Expense expense) {
        try{
            expenseDao.insert(expense);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateExpense(Expense expense) {
        try{
            expenseDao.update(expense);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateExpenseCategory(String expenseId, String newCategory) {
        try{
            if (newCategory.equalsIgnoreCase("Shopping")){
                expenseDao.updateIconId(expenseId, R.drawable.baseline_shopping_cart_24);
            } else if (newCategory.equalsIgnoreCase("Cibo")){
                expenseDao.updateIconId(expenseId, R.drawable.baseline_fastfood_24);
            } else if (newCategory.equalsIgnoreCase("Trasporto")){
                expenseDao.updateIconId(expenseId, R.drawable.baseline_directions_bus_24);
            } else if (newCategory.equalsIgnoreCase("Alloggio")){
                expenseDao.updateIconId(expenseId, R.drawable.baseline_hotel_24);
            } else if (newCategory.equalsIgnoreCase("Cultura")){
                expenseDao.updateIconId(expenseId, R.drawable.baseline_museum_24);
            } else if (newCategory.equalsIgnoreCase("Svago")){
                expenseDao.updateIconId(expenseId, R.drawable.baseline_attractions_24);
            }
            expenseDao.updateCategory(expenseId, newCategory);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateExpenseDescription(String expenseId, String newDescription) {
        try{
            expenseDao.updateDescription(expenseId, newDescription);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateExpenseAmount(String expenseId, String newAmount) {
        try{
            expenseDao.updateAmount(expenseId, newAmount);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateExpenseDate(String expenseId, String newDate) {
        try{
            expenseDao.updateDate(expenseId, newDate);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateExpenseIsSelected(String expenseId, boolean newIsSelected) {
        try{
            expenseDao.updateIsSelected(expenseId, newIsSelected);
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void deleteExpense(Expense expense) {
        try{
            expenseDao.delete(expense);
            expenseCallback.onSuccessDeleteFromLocal();
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void deleteAllExpenses(List<Expense> expenses) {
        try{
            expenseDao.deleteAll(expenses);
            expenseCallback.onSuccessDeleteFromLocal();
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void getAllExpenses() {
        try{
            expenseCallback.onSuccessFromLocal(expenseDao.getAll(diaryId));
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void getSelectedExpenses() {
        try{
            expenseCallback.onSuccessSelectionFromLocal(expenseDao.getSelectedExpenses(diaryId));
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void getFilteredExpenses(String category) {
        try{
            expenseCallback.onSuccessFilterFromLocal(expenseDao.getFilteredExpenses(diaryId, category));
        } catch (Exception e){
            expenseCallback.onFailureFromLocal(e);
        }
    }
}
