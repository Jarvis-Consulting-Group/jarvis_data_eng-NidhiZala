describe('Todo App', () => {
    beforeEach(() => {
      cy.visit('http://localhost:4200');
    });
  
    it('should add a new task', () => {
      const taskName = 'New Task';
  
      // Type the task name in the input field
      cy.get('input[type="text"]').type(taskName);
  
      // Click the add button
      cy.get('span').click();
  
      // Verify that the new task is added to the list
      cy.get('li').contains(taskName).should('be.visible');
    });
  
    it('should delete a task', () => {
      const taskName = 'Task to delete';
  
      // Add a task to the list
      cy.get('input[type="text"]').type(taskName);
      cy.get('span').click();
  
      // Delete the task
      cy.contains('li', taskName).within(() => {
        cy.get('i').click();
      });
  
      // Verify that the task is deleted
      cy.contains('li', taskName).should('not.exist');
    });
  });
  