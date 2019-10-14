context('Report Management Function', () => {
    beforeEach(() => {
        cy.visit('https://asd-demo-app-naomi.herokuapp.com/Reports.html')
    })

    it('Available Reports', () => {
        cy.contains('Transaction');
        cy.contains('Property');
        cy.contains('Auction')
    })
    it('Transaction Report Shows Transactions', () => {
        cy.get('button[id=transaction]').click();
        cy.contains('transaction')
    })
})