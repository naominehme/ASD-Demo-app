context('Login Function', () => {
  beforeEach(() => {
    cy.visit('https://asd-demo-app-naomi.herokuapp.com/login.html')
  })

  it('User Not Found', () => {
  const username = 'test123';
  const password = '123';
  cy.get('input[id=username]').type(username);
  cy.get('input[id=password]').type(password);
  cy.get('button[id=login]').click();
  cy.contains('User Not Found')
})
  it('User Forgot Password', () => {
    const username = 'harry123';
    const dob = '1999-11-12';
    const password = '123ee';
    cy.contains('Forgot Password').click()
    cy.get('input[id=username]').type(username);
    cy.get('input[id=dob]').type(dob);
    cy.get('input[id=password]').type(password);
    cy.contains('Reset Password').click()
    cy.contains('Password successfully reset')
  })
it('User Found and Logged in and Logged Out', () => {
  const username = 'naomi12';
  const password = 'password123';
  cy.get('input[id=username]').type(username);
  cy.get('input[id=password]').type(password);
  cy.get('button[id=login]').click();
})
})