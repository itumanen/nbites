#include "WorldView.h"
#include <iostream>
#include <string>
#include <QIntValidator>
#include <QDebug>

namespace tool {
namespace worldview {

WorldView::WorldView(QWidget* parent)
    : portals::Module(),
      QWidget(parent),
      commThread("comm", COMM_FRAME_LENGTH_uS),
      wviewComm(16,0),
      sharedBallOut(base()),
      locOut(base()),
      wviewShared(1),
      newTeam(0),
      mutex()
{
    commThread.addModule(*this);
    commThread.addModule(wviewComm);
    commThread.addModule(wviewShared);

#ifdef USE_LAB_FIELD
    fieldPainter = new WorldViewPainter(this, 2.);
#else
    fieldPainter = new WorldViewPainter(this, 1.);
#endif

    QHBoxLayout *mainLayout = new QHBoxLayout(this);

    QHBoxLayout *field = new QHBoxLayout();
    field->addWidget(fieldPainter);

    QVBoxLayout *rightBar = new QVBoxLayout();

    QVBoxLayout *options = new QVBoxLayout();
    options->setAlignment(Qt::AlignTop);
    startButton = new QPushButton(QString("Start World Viewer"));
    flipButton = new QPushButton(QString("FLIP"));
    options->addWidget(flipButton);
    options->addWidget(startButton);

    QHBoxLayout *teamLayout = new QHBoxLayout();
    QLabel *teamLabel = new QLabel(tr("Listening to Team: "));
    teamSelector = new QLineEdit(tr("16"));
    QValidator *teamVal = new QIntValidator(1, 255);
    teamSelector->setValidator(teamVal);
    teamLayout->addWidget(teamLabel);
    teamLayout->addWidget(teamSelector);
    options->addLayout(teamLayout);

    connect(teamSelector, SIGNAL(editingFinished()), this, SLOT(teamChanged()));

    QVBoxLayout *stateLayout = new QVBoxLayout();
    stateLayout->setAlignment(Qt::AlignTop);

    QGroupBox *stateBox = new QGroupBox(tr("Robot States"));
    QVBoxLayout *boxLayout = new QVBoxLayout();
    for (int i = 0; i < NUM_PLAYERS_PER_TEAM; ++i)
    {
        roleLabels[i] = new QLabel(tr("Inactive"));
    }
    QHBoxLayout *p1Layout = new QHBoxLayout();
    QLabel *p1Label = new QLabel(tr("Player 1: "));
    QHBoxLayout *p2Layout = new QHBoxLayout();
    QLabel *p2Label = new QLabel(tr("Player 2: "));
    QHBoxLayout *p3Layout = new QHBoxLayout();
    QLabel *p3Label = new QLabel(tr("Player 3: "));
    QHBoxLayout *p4Layout = new QHBoxLayout();
    QLabel *p4Label = new QLabel(tr("Player 4: "));
    QHBoxLayout *p5Layout = new QHBoxLayout();
    QLabel *p5Label = new QLabel(tr("Player 5: "));

    p1Layout->addWidget(p1Label);
    p1Layout->addWidget(roleLabels[0]);

    p2Layout->addWidget(p2Label);
    p2Layout->addWidget(roleLabels[1]);

    p3Layout->addWidget(p3Label);
    p3Layout->addWidget(roleLabels[2]);

    p4Layout->addWidget(p4Label);
    p4Layout->addWidget(roleLabels[3]);

    p5Layout->addWidget(p5Label);
    p5Layout->addWidget(roleLabels[4]);

    boxLayout->addLayout(p1Layout);
    boxLayout->addLayout(p2Layout);
    boxLayout->addLayout(p3Layout);
    boxLayout->addLayout(p4Layout);
    boxLayout->addLayout(p5Layout);
    boxLayout->setSpacing(20);

    stateBox->setFlat(false);

    stateBox->setLayout(boxLayout);
    stateLayout->addWidget(stateBox);

    rightBar->addLayout(options);
    rightBar->addLayout(stateLayout);

    mainLayout->addLayout(field);
    mainLayout->addLayout(rightBar);

    this->setLayout(mainLayout);

    connect(startButton, SIGNAL(clicked()), this, SLOT(startButtonClicked()));
    connect(flipButton, SIGNAL(clicked()), this, SLOT(flipButtonClicked()));

    for (int i = 0; i < NUM_PLAYERS_PER_TEAM; ++i)
    {
        commIn[i].wireTo(wviewComm._worldModels[i]);
        wviewShared.worldModelIn[i].wireTo(wviewComm._worldModels[i]);
    }

    wviewShared.ballIn.wireTo(&sharedBallOut,true);
    wviewShared.locIn.wireTo(&locOut,true);

    sharedIn.wireTo(&wviewShared.sharedBallOutput);
}


void WorldView::run_()
{
    mutex.lock();
    if (newTeam)
    {
        wviewComm.setTeamNumber(newTeam);
        newTeam = 0;

        qDebug() << "World View now listening to team: " << wviewComm.teamNumber();
    }

    for (int i = 0; i < NUM_PLAYERS_PER_TEAM; ++i)
    {
        commIn[i].latch();
        fieldPainter->updateWithLocationMessage(commIn[i].message(), i);
        updateStatus(commIn[i].message(), i);

    }

    setSharedBall();
    sharedIn.latch();

    fieldPainter->updateWithSharedBallMessage(sharedIn.message());

    mutex.unlock();
}

void WorldView::setSharedBall()
{
    portals::Message<messages::FilteredBall> sharedBallMessage(0);
    sharedBallMessage.get()->set_distance(commIn[4].message().ball_dist());
    sharedBallMessage.get()->set_bearing(commIn[4].message().ball_bearing());
    sharedBallOut.setMessage(sharedBallMessage);

    portals::Message<messages::RobotLocation> locMessage(0);
    locMessage.get()->set_x(commIn[4].message().my_x());
    locMessage.get()->set_y(commIn[4].message().my_y());
    locMessage.get()->set_h(commIn[4].message().my_h());
    locOut.setMessage(locMessage);
}

void WorldView::flipButtonClicked()
{
    mutex.lock();
    fieldPainter->flipScreen();
    mutex.unlock();
}

void WorldView::startButtonClicked()
{
    mutex.lock();
    commThread.start();
    startButton->setText(QString("Stop World Viewer"));
    disconnect(startButton, SIGNAL(clicked()), this, SLOT(startButtonClicked()));
    connect(startButton, SIGNAL(clicked()), this, SLOT(stopButtonClicked()));
    mutex.unlock();
}

void WorldView::stopButtonClicked()
{
    mutex.lock();
    commThread.stop();
    startButton->setText(QString("Start World Viewer"));
    disconnect(startButton, SIGNAL(clicked()), this, SLOT(stopButtonClicked()));
    connect(startButton, SIGNAL(clicked()), this, SLOT(startButtonClicked()));
    mutex.unlock();
}

void WorldView::teamChanged()
{
    mutex.lock();
    newTeam = teamSelector->text().toInt();
    mutex.unlock();
}

void WorldView::updateStatus(messages::WorldModel msg, int index)
{
    if (!msg.active())
    {
        roleLabels[index]->setText(QString("Inactive"));
    }
    else
    {
        roleLabels[index]->setText(roles[msg.role() - 1]);
    }
}

} //namespace worldview
} //namespace tool
