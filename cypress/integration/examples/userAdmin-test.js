context('User Admin function test', () => {
    beforeEach(() => {
        cy.visit('https://asd-demo-app-naomi.herokuapp.com/staffPortal.html')
       
    })


        describe('show user detail page', () => {
            it('Available function', () => {

                cy.contains('User')
            })
            
        })

})



