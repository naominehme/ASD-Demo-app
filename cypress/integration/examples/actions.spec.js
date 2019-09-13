context('Actions', () => {
  beforeEach(() => {
    cy.visit('https://asd-demo-app-249308.appspot.com/login.html')
  })

  it('User Not Found', () => {
  const username = 'test123';
  const password = '123';
  cy.get('input[id=username]').type(username);
  cy.get('input[id=password]').type(password);
  cy.get('button[id=login]').click();
  cy.contains('User Not Found')
})

it('User Found and Logged in', () => {
  const username = 'naomi12';
  const password = 'password123';
  cy.get('input[id=username]').type(username);
  cy.get('input[id=password]').type(password);
  cy.get('button[id=login]').click();
  cy.contains('Naomi')
})
})